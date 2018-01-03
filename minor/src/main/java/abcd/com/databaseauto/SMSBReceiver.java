package abcd.com.databaseauto;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class SMSBReceiver extends BroadcastReceiver {
    private SharedPreferences my_pref;
    private String word;

    public static final String SMS_BUNDLE  ="pdus";
    private String smsBody;
    private String smsMessagestr;


    @Override
    public void onReceive(Context context, Intent intent) {

        my_pref = context.getSharedPreferences("my_pref",MODE_PRIVATE);
        word = my_pref.getString("ques", "");
        Log.d("codekamp",word);

        Log.d("codekamp","Entered");
        Bundle intentExtras = intent.getExtras();
        if(intentExtras!=null)
        {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            smsMessagestr = "";
            for(int i=0;i<sms.length;i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                //smsMessagestr +="SMS From: " + address +"\n";
                smsMessagestr += smsBody;
            }
            Toast.makeText(context, smsMessagestr,Toast.LENGTH_SHORT).show();
            /*MainActivity inst = MainActivity.instance();*/
            /*inst.updateList(smsMessagestr);*/
            // Intent i= new Intent(context,MainActivity.class);
            Log.d("codekamp", "In receiver"+smsMessagestr);
            Log.d("codekamp","Receiver"+smsBody);
            Log.d("codekamp","secret"+word);
            Log.d("codekamp",""+(smsMessagestr.equalsIgnoreCase(word)));
            if(smsMessagestr.equalsIgnoreCase(word)) {
                Log.d("codekamp","match");
                Intent i = new Intent(context, SMService.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startService(i);
            }
            else
            {
                Log.d("codekamp"," donotmatch");
            }

            //context.startActivity(i);


        }
    }
}

