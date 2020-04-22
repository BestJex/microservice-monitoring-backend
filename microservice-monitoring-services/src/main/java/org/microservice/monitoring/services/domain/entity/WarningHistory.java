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
 * @author miaoyang.chen@hand-china.com 2020-04-21 22:30:54
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "microservice_warning_history")
public class WarningHistory extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_WARNING_TYPE = "warningType";
    public static final String FIELD_WARNING_TITLE = "warningTitle";
    public static final String FIELD_WARNING_STATUS = "warningStatus";
    public static final String FIELD_WARNING_CONTENT = "warningContent";
    public static final String FIELD_WARNING_RECIPIENT = "warningRecipient";
    public static final String FIELD_WARNING_SENDER = "warningSender";

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
    private String warningType;
	@ApiModelProperty(value = "")
	private String warningTitle;
   @ApiModelProperty(value = "")
    private Long warningStatus;
   @ApiModelProperty(value = "")    
    private String warningContent;
   @ApiModelProperty(value = "")    
    private String warningRecipient;
   @ApiModelProperty(value = "")    
    private String warningSender;

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
	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
    /**
     * @return 
     */
	public Long getWarningStatus() {
		return warningStatus;
	}

	public void setWarningStatus(Long warningStatus) {
		this.warningStatus = warningStatus;
	}
    /**
     * @return 
     */
	public String getWarningContent() {
		return warningContent;
	}

	public void setWarningContent(String warningContent) {
		this.warningContent = warningContent;
	}
    /**
     * @return 
     */
	public String getWarningRecipient() {
		return warningRecipient;
	}

	public void setWarningRecipient(String warningRecipient) {
		this.warningRecipient = warningRecipient;
	}
    /**
     * @return 
     */
	public String getWarningSender() {
		return warningSender;
	}

	public void setWarningSender(String warningSender) {
		this.warningSender = warningSender;
	}

	public String getWarningTitle() {
		return warningTitle;
	}

	public void setWarningTitle(String warningTitle) {
		this.warningTitle = warningTitle;
	}
}
