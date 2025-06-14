package com.java.jsf;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl implements UserDao {

	static SessionFactory sessionFactory;
	Session session;

	static {
		sessionFactory = SessionHelper.getConnection();
	}
	private int adminId = 2;
	private int groupId;
	private List<GroupMembers> allUsersOfAGroup;
	private double advanceCollected;
	private double totalExpenditure;
	private List<KeyValues> getPayUser;
	private List<KeyValues> giveUser;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public List<GroupMembers> getAllUsersOfAGroup() {
		return allUsersOfAGroup;
	}

	public void setAllUsersOfAGroup(List<GroupMembers> allUsersOfAGroup) {
		this.allUsersOfAGroup = allUsersOfAGroup;
	}

	public List<KeyValues> getGetPayUser() {
		return getPayUser;
	}

	public void setGetPayUser(List<KeyValues> getPayUser) {
		this.getPayUser = getPayUser;
	}

	public List<KeyValues> getGiveUser() {
		return giveUser;
	}

	public void setGiveUser(List<KeyValues> giveUser) {
		this.giveUser = giveUser;
	}

	public double getAdvanceCollected() {
		return advanceCollected;
	}

	public void setAdvanceCollected(double advanceCollected) {
		this.advanceCollected = advanceCollected;
	}

	public double getTotalExpenditure() {
		return totalExpenditure;
	}

	public void setTotalExpenditure(double totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@Override
	public String addUserDao(Users user) {
		session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(user);
		trans.commit();
		return "DashBoard.jsf?faces-redirect=true";
	}

	@Override
	public List<Users> showUserDao() {
		session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Users.class);
		System.out.println(cr);
		return cr.list();
	}

	@Override
	public String addGroup(TravelGroup travelGroup) {
		System.out.println(travelGroup);
		session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(travelGroup);
		trans.commit();
		System.out.println("Got");
		return "DashBoard.jsf?faces-redirect=true";
	}

	@Override
	public String addGroupMembers(GroupMembers groupMember) {
		groupMember.setGmId(2);
		System.out.println(groupMember);
		session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(groupMember);
		trans.commit();
		return "DashBoard.jsf?faces-redirect=true";
	}

	public String settlement(int grpId) {
	    session = sessionFactory.openSession();

	    // Retrieve admin user ID of the group
	    Criteria groupCriteria = session.createCriteria(TravelGroup.class);
	    groupCriteria.add(Restrictions.eq("groupId", grpId));
	    TravelGroup groupEntity = (TravelGroup) groupCriteria.uniqueResult();
	    this.adminId = groupEntity.getCreatedBy();

	    // Get list of group participants
	    Criteria memberCriteria = session.createCriteria(GroupMembers.class);
	    memberCriteria.add(Restrictions.eq("groupId", grpId));
	    this.allUsersOfAGroup = memberCriteria.list();

	    // Sum of contributions from all members
	    memberCriteria.setProjection(Projections.sum("amountCollected"));
	    Double totalContribution = (Double) memberCriteria.uniqueResult();
	    this.advanceCollected = totalContribution != null ? totalContribution : 0.0;

	    // Number of participants
	    memberCriteria = session.createCriteria(GroupMembers.class);
	    memberCriteria.add(Restrictions.eq("groupId", grpId));
	    memberCriteria.setProjection(Projections.rowCount());
	    long participantCount = (Long) memberCriteria.uniqueResult();

	    // Calculate total group expenses
	    Criteria expenseCriteria = session.createCriteria(DailyExpenses.class);
	    expenseCriteria.add(Restrictions.eq("groupId", grpId));
	    expenseCriteria.setProjection(Projections.sum("amount"));
	    Double totalGroupExpenses = (Double) expenseCriteria.uniqueResult();
	    this.totalExpenditure = totalGroupExpenses != null ? totalGroupExpenses : 0.0;

	    // Calculate equal share per person
	    double individualShare = totalExpenditure / participantCount;

	    // Amount each user actually spent
	    expenseCriteria = session.createCriteria(DailyExpenses.class);
	    expenseCriteria.add(Restrictions.eq("groupId", grpId));
	    expenseCriteria.setProjection(
	        Projections.projectionList()
	            .add(Projections.groupProperty("paidBy"))
	            .add(Projections.sum("amount"))
	    );

	    List<Object[]> paymentData = expenseCriteria.list();
	    Map<Integer, Double> userPaymentMap = new Hashtable<>();
	    for (Object[] entry : paymentData) {
	        userPaymentMap.put((Integer) entry[0], (Double) entry[1]);
	    }

	    getPayUser = new ArrayList<>();
	    giveUser = new ArrayList<>();

	    for (GroupMembers participant : allUsersOfAGroup) {
	        int participantId = participant.getUserId();
	        double totalPaid = userPaymentMap.getOrDefault(participantId, 0.0);
	        double balance = totalPaid - individualShare;

	        if (balance > 0) {
	            getPayUser.add(new KeyValues(participantId, Math.round(balance * 100.0) / 100.0));
	        } else if (balance < 0) {
	            giveUser.add(new KeyValues(participantId, Math.round(Math.abs(balance) * 100.0) / 100.0));
	        }
	    }

	    return null;
	}


	@Override
	public String addDailyExpenses(DailyExpenses dailyExpenses) {
		session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(dailyExpenses);
		trans.commit();
		return "DashBoard.jsf?faces-redirect=true";
	}

	@Override
	public List<DailyExpenses> showAllExpenses(int groupId) {
		session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(DailyExpenses.class);
		cr.add(Restrictions.eq("groupId", groupId));
		List<DailyExpenses> dailyExpenses = cr.list();
		return dailyExpenses;
	}

	@Override
	public List<TravelGroup> showGroups() {
		session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(TravelGroup.class);
		System.out.println(cr);
		return cr.list();
	}

	@Override
	public List<GroupMembers> showGroupMembers() {
		session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(GroupMembers.class);
		System.out.println(cr);
		return cr.list();
	}

}
