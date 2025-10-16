package pl.coderslab.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

//import com.sun.istack.internal.Nullable;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client {

    // ✅ Make sure this exists:
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @NotBlank(message = "Tên khách hàng không được để trống")
	private String name;

	@Nullable
	private String abbreviation;

	private String status;

	@NotBlank(message = "Lĩnh vực hoạt động không được để trống")
	private String field;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate approachTime;

	@NotBlank(message = "Tên dự án không được để trống")
	private String projectName;

	@DecimalMin(value = "0.0", inclusive = false, message = "Ngân sách dự án phải lớn hơn 0")
//	@Digits(integer = 15, fraction = 2, message = "Ngân sách không hợp lệ")
	private BigDecimal projectBudget;

	@NotBlank(message = "Nguồn tiếp cận không được để trống")
	private String source;

	@NotBlank(message = "Đánh giá cơ hội không được để trống")
	private String chance;

	@NotBlank(message = "Phân loại không được để trống")
	private String category;

	private LocalDate created;

//	@OneToOne(cascade = CascadeType.ALL, optional = true)
//	private ContactPerson contactPerson;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
}
