package com.example.gshare.ModelClasses.Sort;

import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import java.util.ArrayList;

public class Sort {

    public static final char LENDING = 'L';
    public static final char BORROWING = 'B';


    public static final int ALL = 0;
    public static final int EDUCATION = 1;
    public static final int ELECTRONIC = 2;
    public static final int TRANSPORT = 3;
    public static final int STATIONARY = 4;
    public static final int PET = 5;
    public static final int LECTURE_NOTES = 6;
    public static final int BOOKS = 7;
    public static final int OTHER = 8;

    public static ArrayList<Notice> sortByKeyWord ( ArrayList<Notice> noticeList , String keyWord , char noticeType ) {//TESTED AND WORKS
        ArrayList<Notice> sortedList = new ArrayList<Notice>();

        if( noticeType == BORROWING){
            noticeList = getBorrowings( noticeList );
        }
        if( noticeType == LENDING){
            noticeList = getLendings( noticeList );
        }
        for ( int i = 0 ; i < noticeList.size() ; i++ ) {
            if ( noticeList.get(i).getName().indexOf(keyWord) != -1 ) {
                sortedList.add(noticeList.get(i));
            }
        }

        return sortedList;
    }

    public static ArrayList<Notice> sortByCategory ( ArrayList<Notice> noticeList , int categoryType, char noticeType ) {//TESTED AND WORKS
        ArrayList<Notice> sortedList = new ArrayList<Notice>();

        if( noticeType == BORROWING){
            noticeList = getBorrowings( noticeList );
        }
        if( noticeType == LENDING){
            noticeList = getLendings( noticeList );
        }
        for ( int i = 0 ; i < noticeList.size() ; i++ ) {
            if ( noticeList.get(i).getCategory() == categoryType ) {
                sortedList.add(noticeList.get(i));
            }
        }

        return sortedList;
    }

    public static ArrayList<Notice> sortByGInterval ( ArrayList<Notice> noticeList , int startG , int endG  ) {//TESTED AND WORK
        ArrayList<Notice> sortedList = new ArrayList<Notice>();

        noticeList = getLendings( noticeList );

        for ( int i = 0 ; i < noticeList.size(); i++ ) {
            if ( noticeList.get(i).getG() <= endG && startG <= noticeList.get(i).getG() ) {
                sortedList.add(noticeList.get(i));
            }
        }

        return sortedList;
    }

    public static ArrayList<Notice> sortByDistance ( ArrayList<Notice> noticeList , User user , int interval, char noticeType ) {//TESTED AND PROBABLY IT WORKS HARD TO SEE
        ArrayList<Notice> sortedList = new ArrayList<Notice>();

        if( noticeType == BORROWING){
            noticeList = getBorrowings( noticeList );
        }
        if( noticeType == LENDING){
            noticeList = getLendings( noticeList );
        }
        for ( int i = 0 ; i < noticeList.size() ; i++ ) {
            if ( user.getLocation().distanceFrom(noticeList.get(i).getLocation()) <= interval ){
                sortedList.add(noticeList.get(i));
            }
        }

        for( int j = 0; j < sortedList.size(); j++ ) {
            int smallestPos = j;

            for (int i = j; i < sortedList.size(); i++) {
                if (user.getLocation().distanceFrom(sortedList.get(i).getLocation()) < user.getLocation().distanceFrom(sortedList.get(smallestPos).getLocation()))
                    smallestPos = i;
            }

            Notice temp = sortedList.get(smallestPos);
            sortedList.set( smallestPos, sortedList.get(j) );
            sortedList.set( j , temp );

        }


        return sortedList;
    }

    public static ArrayList<Notice> sortByBookmark ( ArrayList<Notice> noticeList , User user, char noticeType ) {//TESTED AND WORKS
        ArrayList<Notice> sortedList = new ArrayList<Notice>();

        if( noticeType == BORROWING){
            noticeList = getBorrowings( noticeList );
        }
        if( noticeType == LENDING){
            noticeList = getLendings( noticeList );
        }
        for ( int i = 0 ; i < noticeList.size() ; i++ ) {
            for (int j = 0; j < user.getBookmarkList().size(); j++) {
                if (noticeList.get( i ).getNoticeOwner().equals(user.getBookmarkList().get( j ) ) ) {
                    sortedList.add( noticeList.get( i ) );
                }
            }
        }

        return sortedList;
    }

    public static ArrayList<Notice> sortByLexiography ( ArrayList<Notice> noticeList, char noticeType ) {//TESTED AND WORKS

        if( noticeType == BORROWING){
            noticeList = getBorrowings( noticeList );
        }
        if( noticeType == LENDING){
            noticeList = getLendings( noticeList );
        }

        for ( int i = 0 ; i < noticeList.size() ; i++ ) {
            int smallestIndex = i;

            for ( int j = i ; j < noticeList.size() ; j++ ) {
                if ( noticeList.get(smallestIndex).getName().compareTo(noticeList.get(j).getName()) > 0 ) {
                    smallestIndex = j;
                }
            }

            Notice temp = noticeList.get(smallestIndex);
            noticeList.set( smallestIndex , noticeList.get(i) );
            noticeList.set(i , temp);
        }
        return noticeList;
    }

    public static ArrayList<Notice> sortByPostTime ( ArrayList<Notice> noticeList, char noticeType ){//TESTED AND WORKS

        if( noticeType == BORROWING){
            noticeList = getBorrowings( noticeList );
        }
        if( noticeType == LENDING){
            noticeList = getLendings( noticeList );
        }
        for ( int i = 0 ; i < noticeList.size() ; i++ ) {
            int smallestIndex = i;

            for (int j = i; j < noticeList.size(); j++) {
                if (noticeList.get(smallestIndex).getPostingTime() > noticeList.get(j).getPostingTime()) {
                    smallestIndex = j;
                }
            }

            Notice temp = noticeList.get( smallestIndex );
            noticeList.set(smallestIndex, noticeList.get(i));
            noticeList.set(i, temp);
        }
        return noticeList;
    }

    public static ArrayList< Notice > getLendings( ArrayList<Notice> noticeList ){//TESTED AND WORKS
        ArrayList< Notice > sortedList = new ArrayList<Notice>();

        for( int i = 0; i < noticeList.size(); i++ ){
            if( noticeList.get(i).getNoticeType() == Notice.LEND_NOTICE){
                sortedList.add( noticeList.get(i) );
            }
        }
        return sortedList;

    }
    public static ArrayList< Notice > getBorrowings( ArrayList<Notice> noticeList ){//TESTED AND WORKS
        ArrayList< Notice > sortedList = new ArrayList<Notice>();

        for( int i = 0; i < noticeList.size(); i++ ){
            if( noticeList.get(i).getNoticeType() == Notice.BORROW_NOTICE){
                sortedList.add( noticeList.get(i) );
            }
        }
        return sortedList;

    }

    /**
     * Not tested added recently
     * @param noticeList
     * @return
     */
    public static ArrayList< Notice > randomize( ArrayList<Notice> noticeList ){
        for( int i = 0; i < noticeList.size(); i++ ) {
            int random = (int) (Math.random() * noticeList.size()) + 1;
            Notice temp = noticeList.get(random);
            noticeList.set( random, noticeList.get(i) );
            noticeList.set( i , temp );
        }
        return noticeList;
        
    }



}