package org.travel2code.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.travel2code.enums.TapType;
import org.travel2code.enums.TripStatus;
import org.travel2code.requestModels.TapRequestInfo;
import org.travel2code.responseModels.UserTripInfo;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TapRequestsProcessorTest {

    @InjectMocks
    TapRequestsProcessor tapRequestsProcessor;

    @Test
    public void testCompletedTripResponse() {
        TapRequestInfo tapRequestInfo = new TapRequestInfo(1, "26-01-2023 15:00:00", TapType.ON, "Stop1", "Company1", "Bus37", "12345" );
        TapRequestInfo tapRequestInfo1 = new TapRequestInfo(2, "26-01-2023 15:05:00", TapType.OFF, "Stop2", "Company1", "Bus37", "12345" );
        List<TapRequestInfo> tapRequestInfoList = Arrays.asList(tapRequestInfo, tapRequestInfo1);

        List<UserTripInfo> userTripInfo = tapRequestsProcessor.processTapRequests(tapRequestInfoList);

        assertThat(userTripInfo).isNotNull();
        assertThat(userTripInfo.get(0).getStatus()).isEqualTo(TripStatus.Completed);

    }

    @Test
    public void testIncompleteTripResponse() {
        TapRequestInfo tapRequestInfo = new TapRequestInfo(1, "26-01-2023 15:00:00", TapType.ON, "Stop1", "Company1", "Bus37", "12345" );
        List<TapRequestInfo> tapRequestInfoList = List.of(tapRequestInfo);

        List<UserTripInfo> userTripInfo = tapRequestsProcessor.processTapRequests(tapRequestInfoList);

        assertThat(userTripInfo).isNotNull();
        assertThat(userTripInfo.get(0).getStatus()).isEqualTo(TripStatus.Incomplete);

    }

    @Test
    public void testCancelledTripResponse() {
        TapRequestInfo tapRequestInfo = new TapRequestInfo(1, "26-01-2023 15:00:00", TapType.ON, "Stop1", "Company1", "Bus37", "12345" );
        TapRequestInfo tapRequestInfo1 = new TapRequestInfo(2, "26-01-2023 15:02:00", TapType.OFF, "Stop1", "Company1", "Bus37", "12345" );
        List<TapRequestInfo> tapRequestInfoList = Arrays.asList(tapRequestInfo, tapRequestInfo1);

        List<UserTripInfo> userTripInfo = tapRequestsProcessor.processTapRequests(tapRequestInfoList);

        assertThat(userTripInfo).isNotNull();
        assertThat(userTripInfo.get(0).getStatus()).isEqualTo(TripStatus.Cancelled);

    }
}
