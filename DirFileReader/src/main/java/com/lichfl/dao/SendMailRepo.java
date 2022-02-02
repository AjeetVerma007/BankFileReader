package com.lichfl.dao;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SendMailRepo {

	private final Logger logger = LoggerFactory.getLogger(SendMailRepo.class);

	@Autowired
	private EntityManager entityManager;
	String outMsg = null;
	StoredProcedureQuery query = null;

	public String SendMail() {
		try {
			query = this.entityManager.createStoredProcedureQuery("pr_sftp_mail_send");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		query.registerStoredProcedureParameter("OUT_MSG", String.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("IN_MODULE_NAME", String.class, ParameterMode.IN);
		query.setParameter("IN_MODULE_NAME", "DEPOSITS");

		try {
			outMsg = (String) query.getOutputParameterValue("OUT_MSG");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("outMsg" + outMsg);
		if (outMsg.equals("SUCCESS")) {
			return outMsg + " : Email has been sent successfully";
		} else {
			return "Email sending failed";
		}
	}
}
