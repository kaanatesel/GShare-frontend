package com.example.gshare.ModelClasses.ChatModel;

import com.example.gshare.ModelClasses.User.User;
import java.util.Calendar;

/**
 * The basic unit of a message.
 *
 * @date 12.12.2019
 */
public class Message {

    private String msg;
    private Calendar calendar;
    private String time;
    private long millisecond;
    private User reciever;
    private User sender;

    /**
     * The constructor of the message class.
     *
     * @param msg the string message
     */
    public Message( String msg, User reciever, User sender ) {
        this.msg = msg;
        calendar = Calendar.getInstance();
        time = getTime();
        millisecond = createMillisecond();
        this.reciever = reciever;
        this.sender = sender;
    }

    /**
     * Use this for database
     * @param msg
     * @param time
     */
    public Message( String msg , String time, long msecond , User reciever , User sender ) {
        this.msg = msg;
        this.time = time;
        this.millisecond = msecond;
        this.reciever = reciever;
        this.sender = sender;
    }

    /**
     * Gets the message string.
     *
     * @return msg message.
     */
    public String getMessage() {
        return msg;
    }

    /**
     * Gets the current time which shows the full date hour and minute.
     *
     * @return str the Time string.
     */
    private String getTime() {//TESTED AND WORKS
        String newMinute = calendar.get( Calendar.MINUTE ) + "";
        if(calendar.MINUTE / 10 == 0 ){
            newMinute = 0 + "" + calendar.get( Calendar.MINUTE );
        }
        String str = "" + calendar.get( Calendar.DATE ) + "." + (calendar.get( Calendar.MONTH ) + 1) + "." +
                calendar.get( Calendar.YEAR ) + " " + fixTime( calendar.get( Calendar.HOUR_OF_DAY ) + "" ) + ":" + fixTime( newMinute );
        return str;
    }



    public String getCurrentTime() {
        return time;
    }

    /**
     *
     * @param msg
     * @return -1 if this object's message created earlier
     * @return 1 if this object's message created later
     */
    public int compareTo( Message msg ){
        if( this.getMillisecond() < msg.getMillisecond() ){
            return -1;
        }
        if( this.getMillisecond() > msg.getMillisecond() ) {
            return 1;
        }
        return 0;
    }

   private long createMillisecond() {
       return calendar.getTimeInMillis();
   }
   public long getMillisecond() {
        return millisecond;
    }
    public User getReciever(){
        return reciever;
    }
    public User getSender(){
        return sender;
    }

    /**
     * Testing purposes only
     * @return
     */
    public String toString(){
        return msg ;//+ "\n" + time;
    }

    /**
     * Only for fixing time showing
     * @param time
     * @return
     */
    private static String fixTime( String time ){
        if(time.length() == 1 ){
            return 0 + time;
        }
        return time;
    }
}