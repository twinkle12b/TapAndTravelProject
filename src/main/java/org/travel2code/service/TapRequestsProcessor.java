package org.travel2code.service;

import org.travel2code.enums.TapType;
import org.travel2code.enums.TripStatus;
import org.travel2code.requestModels.TapRequestInfo;
import org.travel2code.responseModels.UserTripInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TapRequestsProcessor {

    private static final String STOP_1 = "Stop1";
    private static final String STOP_2 = "Stop2";
    private static final String STOP_3 = "Stop3";
    private static final Double TRIP_AMT_12_21 = 3.25;
    private static final Double TRIP_AMT_23_32 = 5.50;
    private static final Double TRIP_AMT_13_31 = 7.30;

    public List<UserTripInfo> processTapRequests(List<TapRequestInfo> tapRequestInfoList) {

        TapRequestsProcessor tapRequestsProcessor = new TapRequestsProcessor();
        //Logic to process all Tap on Requests and generate UserTripResponses
        List<UserTripInfo> userTripInfos = tapRequestInfoList.stream()
                .filter(req -> req.getTapType().equals(TapType.ON))
                .map(tapRequestsProcessor::calculateTapOnResponse).collect(Collectors.toList());

        //Logic to process all Tap off requests and update corresponding UserTripResponses
        tapRequestInfoList.stream()
                .filter(req -> req.getTapType().equals(TapType.OFF))
                .forEach(req -> tapRequestsProcessor.calculateTapOffResponse(req, userTripInfos));
        userTripInfos.stream().filter(userTripInfo -> !userTripInfo.getFromStopId().isEmpty()
                        && userTripInfo.getToStopId() == null)
                .forEach(tapRequestsProcessor::calculateIncompleteTrip);

        return userTripInfos;
    }

    private UserTripInfo calculateTapOnResponse(TapRequestInfo req) {
        UserTripInfo userTripInfo = new UserTripInfo();
        userTripInfo.setStarted(req.getDate());
        userTripInfo.setFromStopId(req.getStopId());
        userTripInfo.setCompanyId(req.getCompanyId());
        userTripInfo.setPanId(req.getPan());
        userTripInfo.setBusId(req.getBusId());
        return userTripInfo;
    }

    private void calculateTapOffResponse(TapRequestInfo tapRequestInfo, List<UserTripInfo> existingUserTripInfo) {
        if (!existingUserTripInfo.isEmpty()) {
            Optional<UserTripInfo> responseInfo = existingUserTripInfo.stream()
                    .filter(userTripInfo1 -> {
                        try {
                            return userTripInfo1.getBusId().equals(tapRequestInfo.getBusId())
                                    && checkIfTapOnOffSameDay(userTripInfo1.getStarted(), tapRequestInfo.getDate())
                                    && userTripInfo1.getPanId().equals(tapRequestInfo.getPan());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }).findFirst();
            responseInfo.ifPresent(res -> {
                res.setFinished(tapRequestInfo.getDate());
                res.setToStopId(tapRequestInfo.getStopId());
                try {
                    res.setDurationSecs(calculateDuration(responseInfo.get().getStarted(), responseInfo.get().getFinished()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                res.setStatus(res.getFromStopId().equals(res.getToStopId()) ? TripStatus.Cancelled : TripStatus.Completed);
                res.setChargeAmount(res.getStatus().equals(TripStatus.Completed) ? calculateCharge(res) : 0.0);
            });
        }
    }

    private boolean checkIfTapOnOffSameDay(String requestDate, String responseDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = simpleDateFormat.parse(requestDate);
        Date date2 = simpleDateFormat.parse(responseDate);
        return date1.equals(date2);
    }

    private long calculateDuration(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d1 = sdf.parse(startDate);
        Date d2 = sdf.parse(endDate);
        long difference_In_Time = d2.getTime() - d1.getTime();
        return (difference_In_Time / 1000);
    }

    private void calculateIncompleteTrip(UserTripInfo userTripInfo) {
        if (userTripInfo != null) {
            String startStop = userTripInfo.getFromStopId();
            userTripInfo.setStatus(TripStatus.Incomplete);
            userTripInfo.setChargeAmount(startStop.equals(STOP_1) || startStop.equals(STOP_3) ? TRIP_AMT_13_31 : TRIP_AMT_23_32);
        }
    }

    private Double calculateCharge(UserTripInfo userTripInfo) {
        double amt = 0.0;
        String from = userTripInfo.getFromStopId();
        String to = userTripInfo.getToStopId();
        if (from != null && to != null) {
            if ((from.equals(STOP_1) && to.equals(STOP_2)) || (from.equals(STOP_2) && to.equals(STOP_1))) {
                amt = TRIP_AMT_12_21;
            } else if ((from.equals(STOP_2) && to.equals(STOP_3)) || (from.equals(STOP_3) && to.equals(STOP_2))) {
                amt = TRIP_AMT_23_32;
            } else if ((from.equals(STOP_1) && to.equals(STOP_3)) || (from.equals(STOP_3) && to.equals(STOP_1))) {
                amt = TRIP_AMT_13_31;
            }
            return amt;
        }
        return amt;
    }
}
