package pack1.Model;

import javax.persistence.*;

//@Entity // ERROR
//@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;

	@Column(columnDefinition = "integer default 0")
	int version;
}
