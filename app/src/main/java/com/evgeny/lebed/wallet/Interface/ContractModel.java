package com.evgeny.lebed.wallet.Interface;

import java.util.List;
import java.util.Map;

public interface ContractModel {

    List<String> getPayments();

    void insertPayment(Long date, Double amount, String note);

    void deletePayment(int id);

    List<String> getNotes();

    void insertNote(String note);

    void deleteNote(String note);

    boolean noteExists(String note);


    Map getOptions();



    void setLanguage(String language);

    String getLanguage();

    void updateLanguage(String language);


    void setCurrency(String currency);

    String getCurrency();

    void updateCurrency(String currency);

    void setBudget(Double budget);

    Double getBudget();

    void updateBudget(Double budget);


    boolean getInitialSetup();

    void updateInitialSetup(boolean initialSetup);

    void setStartDate(Integer startDate);
    Integer getStartDate();
    void updateStartDate(Integer startDate);


}
