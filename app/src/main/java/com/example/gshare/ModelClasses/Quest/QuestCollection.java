package com.example.gshare.ModelClasses.Quest;

import java.util.ArrayList;

public class QuestCollection {

    private ArrayList<Quest> questList;

    public QuestCollection( ArrayList<Quest> questList ) {
        this.questList = questList;
    }

    public ArrayList<Quest> getAllQuests() {
        return questList;
    }

    public Quest getQuest( int index ) {
        return questList.get( index );
    }

    public void addNewQuest( Quest q ) {
        questList.add(q);
    }

    public void sortQuestCollection() {
        int count = 0;

        for ( int i = 0 ; i < questList.size() ; i++ ) {
            if ( !questList.get(i).isCompleted() ) {
                count++;
            }
        }

        for ( int i = 0 ; i < count ; i++ ) {
            if ( questList.get(i).isCompleted() ) {
                questList.add( questList.get(i) );
                questList.remove(i);
            }
        }

    }

    public static QuestCollection getQuestCollection(){
        QuestCollection collection = new QuestCollection( new ArrayList<Quest>());

        Quest q1 = new NoticeQuest(0,0);
        Quest q2 = new NoticeQuest(1,10,"Do 5 lendings",5, 0);
        Quest q3 = new NoticeQuest(2,10,"Do 5 borrwings",5, 0);
        Quest q4 = new StarQuest(3,20,"Get five starts from five notices",5,0);
        Quest q5 = new BookmarkQuest(4,5, "Add five bookmarks",5,0);

        collection.addNewQuest(q1);
        collection.addNewQuest(q2);
        collection.addNewQuest(q3);
        collection.addNewQuest(q4);
        collection.addNewQuest(q5);

        return collection;
    }


}