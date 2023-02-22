package controller;

import DAO.AppointmentsDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.AppointmentReporting;
import org.junit.jupiter.api.Test;
import utilities.TimeConv;

import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Testing {
    Stage stage;
    Scene scene;
    Parent root;
    userform_controller userController = new userform_controller();
    report_controller reportController = new report_controller();


    @Test
    void userCheck() throws Exception {
        assertEquals(true,userController.userCheck("admin"));
        assertEquals(true, userController.userCheck("test"));
        assertEquals(false, userController.userCheck("newName"));
        assertEquals(false, userController.userCheck("admin1"));
    }
    @Test
    void uniqueMonthCheckEmpty() throws Exception {
        ObservableList<AppointmentReporting> appointments = FXCollections.observableArrayList();
        AppointmentReporting appointment = new AppointmentReporting();
        Set<Month> testSet = new HashSet<Month>();
        Set<Month> set1 = new HashSet<Month>(reportController.getUniqueMonths(appointments));
        assertEquals(true, set1.isEmpty());


    }
    @Test
    void uniqueMonthCheckRemovesDuplicates() throws Exception {
        ObservableList<AppointmentReporting> appointments = FXCollections.observableArrayList();
        AppointmentReporting appointment = new AppointmentReporting();
        Set<Month> testSet = new HashSet<Month>();
        Set<Month> set1 = new HashSet<Month>(reportController.getUniqueMonths(appointments));
        appointments.addAll(AppointmentsDAOImp.getAllAppointmentsWithContact());
        set1.addAll(reportController.getUniqueMonths(appointments));
        testSet.add(Month.MAY);
        testSet.add(Month.FEBRUARY);
        testSet.add(Month.MARCH);
        assertEquals(testSet, set1);
        System.out.println(testSet + " " +  set1);

    }
    @Test
    void uniqueMonthCheckRemovesFailsIncorrectMonth() throws Exception {
        ObservableList<AppointmentReporting> appointments = FXCollections.observableArrayList();
        AppointmentReporting appointment = new AppointmentReporting();
        Set<Month> testSet = new HashSet<Month>();
        Set<Month> set1 = new HashSet<Month>(reportController.getUniqueMonths(appointments));
        appointments.addAll(AppointmentsDAOImp.getAllAppointmentsWithContact());
        set1.addAll(reportController.getUniqueMonths(appointments));
        testSet.add(Month.MAY);
        testSet.add(Month.FEBRUARY);
        testSet.add(Month.MARCH);
        testSet.add(Month.JUNE);
        assertNotEquals(testSet, set1);
        System.out.println(testSet + " " +  set1);
    }
    @Test
    void uniqueMonthCheckAddsNewMonth() throws Exception {
        ObservableList<AppointmentReporting> appointments = FXCollections.observableArrayList();
        AppointmentReporting appointment = new AppointmentReporting();
        Set<Month> testSet = new HashSet<Month>();
        Set<Month> set1 = new HashSet<Month>(reportController.getUniqueMonths(appointments));
        appointments.addAll(AppointmentsDAOImp.getAllAppointmentsWithContact());
        testSet.add(Month.MAY);
        testSet.add(Month.FEBRUARY);
        testSet.add(Month.MARCH);
        appointments.get(0).setStart(TimeConv.stringToZoneDate("2023-08-15 10:00:00"));
        set1.addAll(reportController.getUniqueMonths(appointments));
        testSet.add(Month.AUGUST);
        assertEquals(testSet, set1);
        System.out.println(testSet + " " + set1);

    }




}