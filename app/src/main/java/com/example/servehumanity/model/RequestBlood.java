package com.example.servehumanity.model;

public class RequestBlood {

    private  String _id, patientName, patientAge, fullAddress, bloodGroup, hospitalName, requirement, needUnit, requirementReason, requiredBefore, user;

    public RequestBlood(String _id, String patientName, String patientAge, String fullAddress, String bloodGroup, String hospitalName, String requirement, String needUnit, String requirementReason, String requiredBefore, String user) {
        this._id = _id;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.fullAddress = fullAddress;
        this.bloodGroup = bloodGroup;
        this.hospitalName = hospitalName;
        this.requirement = requirement;
        this.needUnit = needUnit;
        this.requirementReason = requirementReason;
        this.requiredBefore = requiredBefore;
        this.user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getNeedUnit() {
        return needUnit;
    }

    public void setNeedUnit(String needUnit) {
        this.needUnit = needUnit;
    }

    public String getRequirementReason() {
        return requirementReason;
    }

    public void setRequirementReason(String requirementReason) {
        this.requirementReason = requirementReason;
    }

    public String getRequiredBefore() {
        return requiredBefore;
    }

    public void setRequiredBefore(String requiredBefore) {
        this.requiredBefore = requiredBefore;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
