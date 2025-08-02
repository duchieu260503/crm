package pl.coderslab.entity;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.validation.ValidationGroups;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// ===== Shared Fields =====

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull(message = "Loại hợp đồng không được để trống", groups = {ValidationGroups.InProgress.class, ValidationGroups.DoneDeal.class})
	private ContractType type;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	@NotNull(message = "Khách hàng không được để trống", groups = {ValidationGroups.InProgress.class, ValidationGroups.DoneDeal.class})
	private Client client;

	private String product; // SPDV

	private String status; // Tình trạng hợp đồng

	@NotBlank(message = "Phòng ban chủ trì không được để trống", groups = {ValidationGroups.InProgress.class, ValidationGroups.DoneDeal.class})
	private String unitInCharge;

	@NotBlank(message = "Phân loại hợp đồng không được để trống", groups = {ValidationGroups.InProgress.class, ValidationGroups.DoneDeal.class})
	private String category;

	private String generalProgress;

	private String amLead;

	// ===== In-Progress Only =====

	@NotNull(message = "Giá trị dự kiến không được để trống", groups = ValidationGroups.InProgress.class)
	private Long expectedValue;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Ngày triển khai dự kiến không được để trống", groups = ValidationGroups.InProgress.class)
	private LocalDate expectedLaunch;

	private String nextPlan;

	private String difficulties;

	private Integer expectedSeatsPerMonth;

	private Long expectedRevenuePerMonth;

	private String vcxImplementingUnit;

	@Lob
	private String detailedProgress;

	// ===== Done Deal Only =====

	private String contractCode;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Ngày ký hợp đồng không được để trống", groups = ValidationGroups.DoneDeal.class)
	private LocalDate signedDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Ngày bắt đầu hợp đồng không được để trống", groups = ValidationGroups.DoneDeal.class)
	private LocalDate startDate;

	private Integer durationInMonths;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	private Long totalValue;

	private String revenueStatus;

	private String pakdCode;

	private String pakdContent;

//	private String implementingUnit;

	private String lastClient;

	private String sellingChannel;

//	private Double vcxRatio;

	private Long contractValueNoVat;

	private Long vat;

	private String servicePlatform;

	private String note;

	// ===== Meta Info =====

	private LocalDate created = LocalDate.now();

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User author;

	@ManyToOne
	@JoinColumn(name = "acceptedBy_id")
	private User acceptedBy;

	public Integer getMonth() {
		return durationInMonths;
	}

	public Long getTotal() {
		return totalValue;
	}

	public ContractType getContractType() {
		return type;
	}
}