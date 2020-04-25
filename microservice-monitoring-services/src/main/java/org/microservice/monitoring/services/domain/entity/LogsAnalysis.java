package org.microservice.monitoring.services.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 *
 * @author miaoyang.chen@hand-china.com 2020-04-23 20:44:11
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "microservice_logs_analysis")
public class LogsAnalysis extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_LOG_ID = "logId";
    public static final String FIELD_LOG_HOST = "logHost";
    public static final String FIELD_LOG_LEVEL = "logLevel";
    public static final String FIELD_LOG_CAGETORY = "logCagetory";

    //
    // 业务方法(按public protected private顺序排列)
    // ------------------------------------------------------------------------------

    //
    // 数据库字段
    // ------------------------------------------------------------------------------


    @ApiModelProperty("")
    @Id
    @GeneratedValue
    private Long id;
   @ApiModelProperty(value = "")    
    private String logId;
   @ApiModelProperty(value = "")    
    private String logHost;
   @ApiModelProperty(value = "")    
    private String logLevel;
   @ApiModelProperty(value = "")    
    private String logCagetory;

	//
    // 非数据库字段
    // ------------------------------------------------------------------------------

    //
    // getter/setter
    // ------------------------------------------------------------------------------

    /**
     * @return 
     */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    /**
     * @return 
     */
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
    /**
     * @return 
     */
	public String getLogHost() {
		return logHost;
	}

	public void setLogHost(String logHost) {
		this.logHost = logHost;
	}
    /**
     * @return 
     */
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
    /**
     * @return 
     */
	public String getLogCagetory() {
		return logCagetory;
	}

	public void setLogCagetory(String logCagetory) {
		this.logCagetory = logCagetory;
	}

}
