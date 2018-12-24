package com.evgeny.lebed.wallet.Class;

import android.content.Context;

import com.evgeny.lebed.wallet.R;

import java.util.Calendar;
import java.util.Date;

public class WalletMethods {

    private static Calendar calendar = Calendar.getInstance();
    private static Long today;
    private static Long yesterday;

    public static final String RUSSIAN = "РУССКИЙ", ENGLISH = "ENGLISH";



    public final static Сurrencies
    RUSSIAN_RUBLE = new Сurrencies("\u20BD", R.string.russian_ruble, false),
    BELARUSIAN_RUBLE = new Сurrencies("Br", R.string.belarusian_ruble, false),
    EURO = new Сurrencies("€", R.string.euro, false),
    UKRAINIAN_HRYVNIA = new Сurrencies("₴", R.string.ukrainian_hryvnia, false),
    KAZAKHSTANI_TENGE = new Сurrencies("₸", R.string.kazakhstani_tenge, false),
    ISRAELI_SHEKEL = new Сurrencies("ש", R.string.israeli_shekel, false),
    DOLLAR = new Сurrencies("$", R.string.dollar, false),
    KRONA = new Сurrencies("Kr", R.string.krona, false),
    POUND_STERLING = new Сurrencies("£", R.string.pound_sterling, false),
    POLISH_ZLOTY = new Сurrencies("zł", R.string.polish_złoty, false),
    SWISS_FRANK = new Сurrencies("₣", R.string.swiss_frank, false);

    public static String formatDate(Long date, Context context, String language) {

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        today = calendar.getTimeInMillis();
        yesterday = today - 86400000;

        calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        //Resources.getSystem().getString(R.id.);
        if (today.equals(date)) {

            return context.getResources().getString(R.string.today);

        } else if (yesterday.equals(date)) {

            return context.getResources().getString(R.string.yesterday);

        }
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        String result = "";
        if (language.equals(WalletMethods.ENGLISH)){
            switch (calendar.get(Calendar.MONTH)) {
                case 0:
                    result = context.getString(R.string.january) + " " + day;
                    break;
                case 1:
                    result = context.getString(R.string.february) + " " + day;
                    break;
                case 2:
                    result = context.getString(R.string.march) + " " + day;
                    break;
                case 3:
                    result = context.getString(R.string.april) + " " + day;

                    break;
                case 4:
                    result = context.getString(R.string.may) + " " + day;

                    break;
                case 5:
                    result = context.getString(R.string.june) + " " + day;

                    break;
                case 6:
                    result = context.getString(R.string.july) + " " + day;

                    break;
                case 7:
                    result = context.getString(R.string.august) + " " + day;
                    break;
                case 8:
                    result = context.getString(R.string.september) + " " + day;

                    break;
                case 9:
                    result = context.getString(R.string.october) + " " + day;

                    break;
                case 10:
                    result = context.getString(R.string.november) + " " + day;

                    break;
                case 11:
                    result = context.getString(R.string.december) + " " + day;

                    break;
            }
        }

        else if(language.equals(WalletMethods.RUSSIAN)){
            switch (calendar.get(Calendar.MONTH)) {
                case 0:
                    result = day + " " + context.getString(R.string.january);
                    break;
                case 1:
                    result = day + " " + context.getString(R.string.february);
                    break;
                case 2:
                    result = day + " " + context.getString(R.string.march) ;
                    break;
                case 3:
                    result = day + " " + context.getString(R.string.april) ;

                    break;
                case 4:
                    result = day + " " + context.getString(R.string.may) ;

                    break;
                case 5:
                    result = day + " " + context.getString(R.string.june) ;

                    break;
                case 6:
                    result = day + " " + context.getString(R.string.july) ;

                    break;
                case 7:
                    result =day + " " +  context.getString(R.string.august) ;
                    break;
                case 8:
                    result =day + " " +  context.getString(R.string.september) ;

                    break;
                case 9:
                    result = day + " " + context.getString(R.string.october);

                    break;
                case 10:
                    result = day + " " + context.getString(R.string.november) ;

                    break;
                case 11:
                    result = day + " " + context.getString(R.string.december) ;

                    break;
            }
        }
        return result;
    }
}




