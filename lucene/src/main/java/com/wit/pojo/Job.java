package com.wit.pojo;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "job_info")
public class Job {
    @Id
    private Long id;
    private String companyName;
    private String jobName;
    private String companyAddr;
    private String jobAddr;
    @Column(name = "salary_min")
    private Integer salary;
    private String url;

}
