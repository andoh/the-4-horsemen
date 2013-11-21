package ee.ut.math.tvt.salessystem.domain.controller.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.ViewItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {

	private Session session = HibernateUtil.currentSession();

	private static Connection getHSQLConnection() throws Exception {
	    Class.forName("org.hsqldb.jdbcDriver");
	    System.out.println("Driver Loaded.");
	    String url = "jdbc:hsqldb:hsql://localhost/POS";
	    return DriverManager.getConnection(url, "sa", "");
	  }
	
	private Query query;

	private Transaction ta;

	private SalesSystemModel model;

	public void setModel(SalesSystemModel model) {
		this.model = model;
	}

	public void submitCurrentPurchase(List<SoldItem> goods)
			throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is
		// underaged and
		// cannot buy chupa-chups
		// throw new VerificationFailedException("Underaged!");
		// Date stamp = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// String date = sdf.format(stamp);
		//
		// sdf = new SimpleDateFormat("hh:mm:ss");
		// String time = sdf.format(stamp);
		//
		Double summa = 0.0;
		// Sets next id for correct database insert
		int nextval = 1 + (int) (session
				.createQuery("SELECT DISTINCT MAX(sale_id) FROM SoldItem")
				.uniqueResult());

		for (SoldItem si : goods) {

			// Sums up purchase total
			summa += si.getSum();

			// Gets values for SQL insert
			Long id = si.getId();
			Integer quantity = si.getQuantity();
			Double sum = si.getSum();

			// Insert into table SoldItem values that exist
			try {
				ta = session.beginTransaction();
				query = session.createSQLQuery("INSERT INTO SoldItem"
						+ " (sale_id,stockitem_id,quantity,total)"
						+ " values (" + nextval + "," + id + "," + quantity
						+ "," + sum + ")");
				query.executeUpdate();
				session.getTransaction().commit();
				session.flush();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				ta.rollback();
				e.printStackTrace();
			}
		}

		// Insert total data into HistoryItem
		try {
			ta = session.beginTransaction();
			query = session.createSQLQuery("INSERT INTO HistoryItem"
					+ "(ID,TOTAL) VALUES (" + nextval + "," + summa + ")");

			query.executeUpdate();
			session.getTransaction().commit();
			session.flush();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			ta.rollback();
			e.printStackTrace();
		}

		// In console, not needed, will throw NullPointerException
		// try {
		// model.getCurrentHistoryTableModel().addItem(item);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// }
		model.updateHistoryTab();
		model.updateWareHouse();
		

		// String[] display = {dateStamp,timeStamp,String.valueOf(summa)};

		// XXX - Save purchase
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public void endSession() {
		HibernateUtil.closeSession();
	}

	public List<HistoryItem> loadHistoryTab() {
		List<HistoryItem> result = session.createQuery("from HistoryItem").list();
		return result;
	}

	public List<StockItem> loadWarehouseState() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}

	public List<ViewItem> loadHistoryView(Long p) throws Exception {
		Connection conn = getHSQLConnection();
		System.out.println("ühendatud");
		Statement st= conn.createStatement();
		
		//ResultSet rs = st.executeQuery("SELECT StockItem.id,StockItem.name,StockItem.price,SoldItem.quantity,SoldItem.total,Solditem.datestamp "
		//					+ "FROM SoldItem JOIN StockItem ON StockItem.id=SoldItem.stockitem_id AND SoldItem.sale_id=1");
		ResultSet rs = st.executeQuery("SELECT StockItem.id,StockItem.name,StockItem.price,SoldItem.quantity,SoldItem.total,Solditem.datestamp "
									+ "FROM SoldItem JOIN StockItem ON StockItem.id=SoldItem.stockitem_id AND SoldItem.sale_id="+p);
		
		List<ViewItem> result = new ArrayList<ViewItem>();
		
		//System.out.println(rs);
		//ResultSetMetaData rsmeta = rs.getMetaData();
		
//		int numberofColumns = rsmeta.getColumnCount();
//		System.out.println("veerge on: "+numberofColumns);
		ViewItem temp;
		List<ViewItem> tempList = new ArrayList<ViewItem>();
		while(rs.next())
		{
			//temp=new ViewItem((rs.getObject("ID")),(rs.getObject("NAME")),(rs.getObject("PRICE")),(rs.getObject("QUANTITY")),(rs.getObject("TOTAL")),(rs.getObject("DATESTAMP")));
			Integer newIdT = (Integer) (rs.getObject("ID"));
			String ss = String.valueOf(newIdT);
			Long newId = Long.parseLong(ss);
			
						
			String newName = (String) (rs.getObject("NAME"));
			BigDecimal newPrice = (BigDecimal) (rs.getObject("PRICE"));
								
			Integer newQuantity = (Integer) (rs.getObject("QUANTITY"));
			BigDecimal newTotal = (BigDecimal) (rs.getObject("TOTAL"));
			String newDateStamp = String.valueOf(rs.getObject("DATESTAMP"));		
			
			temp = new ViewItem(newId,newName,newPrice,newQuantity,newTotal,newDateStamp);
			tempList.add(temp);
			
//			System.out.println(rs.getObject("ID"));
//			System.out.println(rs.getObject("NAME"));
//			System.out.println(rs.getObject("PRICE"));
//			System.out.println(rs.getObject("QUANTITY"));
//			System.out.println(rs.getObject("TOTAL"));
//			System.out.println(rs.getObject("DATESTAMP"));
		}
		
		for (int i = 0; i<tempList.size();i++){
			System.out.println(tempList.get(i).toString());
		}
		
		/*for(int i=1;i<=numberofColumns;i++){
			System.out.println("ridade metadata");
			System.out.println("rea number "+i);
			
			//System.out.println(rsmeta.getColumn(i));
		}*/
		st.close();
		conn.close();
		
		/*
		 * ta = session.beginTransaction();
		 */
		//@SuppressWarnings("unchecked")
		/*List<ViewItem> result = (List<ViewItem>) session
			.createSQLQuery(
						"SELECT StockItem.id,StockItem.name,StockItem.price,SoldItem.quantity,SoldItem.total,Solditem.datestamp "
							+ "FROM SoldItem JOIN StockItem ON StockItem.id=SoldItem.stockitem_id AND SoldItem.sale_id=1"
							).list();
		System.out.println(result.get(0));
		*/
		//
		// ("SELECT StockItem.id,SoldItem.sale_id,StockItem.name,StockItem.price,SoldItem.quantity,SoldItem.total,SoldItem.timestamp "
		// +
		// "FROM SoldItem JOIN StockItem WHERE StockItem.id=SoldItem.stockitem_id AND SoldItem.sale_id=3").list();

		// ("SELECT stockitem.id,solditem.sale_id,stockitem.name,stockitem.price,solditem.quantity,solditem.total,solditem.timestamp "
		// +
		// "FROM PUBLIC.SOLDITEM JOIN PUBLIC.STOCKITEM id=stockitem_id where solditem.sale_id="+input).list();
		//
		session.getTransaction().commit();
		session.flush();
		
//		for (int i = 0; i < result.size(); i++)
//		{
//			System.out.println(result.get(i));
//		}
		
		return result;
	}

	// *** OBSOLETE ****
	// List<StockItem> dataset = new ArrayList<StockItem>();
	//
	// StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0,
	// 5);
	// StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0,
	// 8);
	// StockItem frankfurters = new StockItem(3l, "Frankfurters",
	// "Beer sauseges", 15.0, 12);
	// StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0,
	// 100);
	//
	// dataset.add(chips);
	// dataset.add(chupaChups);
	// dataset.add(frankfurters);
	// dataset.add(beer);
	//
	// return dataset;

}
