package in.chennait.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "INSURANCE_PLANS")
public class InsurancePlanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAN_ID")
	private Integer planId;

	@Column(name = "PLAN_NAME")
	private String planName;

	@Column(name = "PLAN_HOLDER_NAME")
	private String planHolderName;

	@Column(name = "PLAN_HOLDER_SSN")
	private Long planHolderSsn;

	@Column(name = "PLAN_STATUS")
	private String planStatus;

}
