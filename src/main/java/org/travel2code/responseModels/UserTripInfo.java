package org.travel2code.responseModels;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.travel2code.enums.TripStatus;


public class UserTripInfo {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "started", required = true)
    private String started;
      @CsvBindByPosition(position = 1)
      @CsvBindByName(column = "finished", required = true)
    private String finished;
     @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "durationSecs", required = true)
    private Long durationSecs;
    @CsvBindByPosition(position = 3)
     @CsvBindByName(column = "fromStopId", required = true)
    private String fromStopId;

     @CsvBindByPosition(position = 4)
     @CsvBindByName(column = "toStopId", required = true)
    private String toStopId;
    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "chargeAmount", required = true)
    private Double chargeAmount;

    @CsvBindByPosition(position = 6)
     @CsvBindByName(column = "companyId", required = true)
    private String companyId;
     @CsvBindByPosition(position = 7)
     @CsvBindByName(column = "panId", required = true)
    private String panId;
     @CsvBindByPosition(position = 8)
      @CsvBindByName(column = "busId", required = true)
    private String busId;

    @CsvBindByPosition(position = 9)
    @CsvBindByName(column = "status", required = true)
    private TripStatus status;


    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }


    public UserTripInfo() {
    }


    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public Long getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(Long durationSecs) {
        this.durationSecs = durationSecs;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public void setFromStopId(String fromStopId) {
        this.fromStopId = fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public void setToStopId(String toStopId) {
        this.toStopId = toStopId;
    }

    public Double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPanId() {
        return panId;
    }

    public void setPanId(String panId) {
        this.panId = panId;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }
}
