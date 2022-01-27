package com.ty.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ty.dto.Invoice;
import com.ty.dto.Item;

public class ItemInvoice {
	private EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
		return entityManagerFactory.createEntityManager();
	}

	public void saveInvoiceAndItem(Invoice invoice, Item item) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(item);
		entityManager.persist(invoice);
		entityTransaction.commit();
	}

	public Invoice getInvoice(int id) {
		EntityManager entityManager = getEntityManager();
		Invoice invoice = entityManager.find(Invoice.class, id);
		if (invoice != null) {
			return invoice;
		} else {
			return null;
		}
	}

	public void deleteInvoice(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Invoice invoice = entityManager.find(Invoice.class, id);
		if (invoice != null) {
			Item item = invoice.getItem();
			entityManager.remove(invoice);
			if (item != null) {
				entityManager.remove(item);
			}
		}
		entityTransaction.commit();
	}

	public void updateInvoice(Invoice invoice) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Invoice invoice2 = entityManager.find(Invoice.class, invoice.getId());
		entityManager.merge(invoice);
		entityTransaction.commit();
	}
}
