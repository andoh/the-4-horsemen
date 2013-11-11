package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private Session session = HibernateUtil.currentSession();
    
	private Query query;
	
	private Transaction ta;
	
	private SalesSystemModel model;
	
	public void setModel(SalesSystemModel model) {
		this.model = model;
	}
		
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		//throw new VerificationFailedException("Underaged!");
		Double summa = 0.0;
		
		int nextval = 1 + (int)(session.createQuery("SELECT DISTINCT MAX(sale_id) FROM SoldItem").uniqueResult());
		
		for (SoldItem si : goods){
			
			summa += si.getSum();
	    	
			//get values for SQL insert
			Long id = si.getId();
			Integer quantity = si.getQuantity();
			Double sum = si.getSum();
			
			try {
				ta = session.beginTransaction();
				query = session.createSQLQuery
						("INSERT INTO SoldItem"
								+ " (sale_id,stockitem_id,quantity,total)"
								+ " values (" + nextval + "," + id + "," + quantity + "," + sum + ")");
				query.executeUpdate();
				session.getTransaction().commit();
				session.flush();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				ta.rollback();
				e.printStackTrace();
			}
			
    	}
		
		try {
			ta = session.beginTransaction();			
			query = session.createSQLQuery("INSERT INTO HistoryItem"
					+ " (ID,TOTAL) VALUES (" + nextval + "," + summa + ")");
			
			query.executeUpdate();
			session.getTransaction().commit();
			session.flush();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			ta.rollback();
			e.printStackTrace();
		}
	
		/*
		HistoryItem item = new HistoryItem(goods, dateStamp, timeStamp, summa);
		
		model.getCurrentHistoryTableModel().addItem(item);
		*/
		//String[] display = {dateStamp,timeStamp,String.valueOf(summa)};
	
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
	
	public List<StockItem> loadWarehouseState() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
		
		
//		*** OBSOLETE ****
//		List<StockItem> dataset = new ArrayList<StockItem>();
//
//		StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
//		StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
//	    StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
//	    StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);
//
//		dataset.add(chips);
//		dataset.add(chupaChups);
//		dataset.add(frankfurters);
//		dataset.add(beer);
//		
//		return dataset;
	}

	
	
	
}
