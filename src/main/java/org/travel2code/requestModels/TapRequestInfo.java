package org.travel2code.requestModels;

import com.opencsv.bean.CsvBindByName;
import org.travel2code.enums.TapType;

public class TapRequestInfo {

 @CsvBindByName(column = "ID")
    private int id;
 @CsvBindByName(column = "DateTimeUTC")
    private String date;
   @CsvBindByName(column = "TapType")
    private TapType tapType;
    @CsvBindByName(column = "StopId")
    private String stopId;
    @CsvBindByName(column = "CompanyId")
    private String companyId;
    @CsvBindByName(column = "BusID")
    private String busId;
    @CsvBindByName(column = "PAN")
    private String pan;
    public TapRequestInfo() {
    }
    public TapRequestInfo(int id, String date, TapType tapType, String stopId, String companyId, String busId, String pan) {
        this.id = id;
        this.date = date;
        this.tapType = tapType;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TapType getTapType() {
        return tapType;
    }

    public void setTapType(TapType tapType) {
        this.tapType = tapType;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
}
