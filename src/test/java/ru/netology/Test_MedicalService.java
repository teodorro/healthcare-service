package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;

public class Test_MedicalService {
    @Test
    public void test_checkBloodPressure_SendMessage_IfDifferentPressures(){
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito
                .when(patientInfoRepository.getById(any()))
                .thenReturn(new PatientInfo("1", "a", "b", LocalDate.now(),
                        new HealthInfo(new BigDecimal(123), new BloodPressure(2, 1))));
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        final Integer[] x = {0};
        Mockito
                .doAnswer(invocationOnMock -> {x[0]++; return null;})
                .when(alertService).send(isA(String.class));
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        medicalService.checkBloodPressure("1", new BloodPressure(3, 1));

        Assertions.assertEquals(1, x[0].intValue());
    }

    @Test
    public void test_checkTemperature_SendMessage_IfDifferentTemperatures(){
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito
                .when(patientInfoRepository.getById(any()))
                .thenReturn(new PatientInfo("1", "a", "b", LocalDate.now(),
                        new HealthInfo(new BigDecimal(123), new BloodPressure(2, 1))));
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        final Integer[] x = {0};
        Mockito
                .doAnswer(invocationOnMock -> {x[0]++; return null;})
                .when(alertService).send(isA(String.class));
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        medicalService.checkTemperature("1", new BigDecimal(1));
        Assertions.assertEquals(1, x[0].intValue());
    }

    @Test
    public void test_checkBloodPressure_NotSendMessage_IfEqualPressures(){
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito
                .when(patientInfoRepository.getById(any()))
                .thenReturn(new PatientInfo("1", "a", "b", LocalDate.now(),
                        new HealthInfo(new BigDecimal(123), new BloodPressure(2, 1))));
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        final Integer[] x = {0};
        Mockito
                .doAnswer(invocationOnMock -> {x[0]++; return null;})
                .when(alertService).send(isA(String.class));
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        medicalService.checkBloodPressure("1", new BloodPressure(2, 1));

        Assertions.assertEquals(0, x[0].intValue());
    }

    @Test
    public void test_checkTemperature_NotSendMessage_IfDifferentPressures(){
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito
                .when(patientInfoRepository.getById(any()))
                .thenReturn(new PatientInfo("1", "a", "b", LocalDate.now(),
                        new HealthInfo(new BigDecimal(123), new BloodPressure(2, 1))));
        SendAlertService alertService = Mockito.mock(SendAlertService.class);
        final Integer[] x = {0};
        Mockito
                .doAnswer(invocationOnMock -> {x[0]++; return null;})
                .when(alertService).send(isA(String.class));
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        medicalService.checkTemperature("1", new BigDecimal(123));
        Assertions.assertEquals(0, x[0].intValue());
    }
}
