package com.example.gshare.ModelClasses.ChatModel;

import java.util.ArrayList;
import java.util.Objects;

import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;

    /**
     * The class Chat for a unit of Chat.
     *
     * @date 15.12.2019
     */
    public class Chat {

        public static final int NOT_AGREED = 0;
        public static final int TERMINATED = 1;
        public static final int AGREED = 2;
        public static final int WAITING_FOR_RETURN = 3;
        public static final int RETURNED = 4;

        private ArrayList<Message> messageList;
        private Notice notice;
        private long timeLeft;
        private User noticeOwner;
        private User customer;
        private int status;

        /**
         * The constructor of the Chat class.
         *
         * @param notice a notice that users chat about.
         * @param noticeOwner the owner of the notice.
         * @param customer the customer who doesn't own the notice.
         */
        public Chat( Notice notice , User noticeOwner , User customer ) {
            this.notice = notice;
            this.noticeOwner = noticeOwner;
            this.customer = customer;
            messageList = new ArrayList<Message>();
            timeLeft = getTimeLeft();
            status = NOT_AGREED;
        }

        /**
         * This method gets all messages as an arraylist.
         *
         * @return messageList the list of all messages in an arraylist.
         */
        public ArrayList<Message> getAllMessage() {
            return messageList;
        }
        public void setMessageList( ArrayList<Message> list ){
            this.messageList = list;
        }

        /**
         * This method gets a specific message in the index of arraylist.
         *
         * @param index a specified index.
         * @return messageList.get( index ) a specified message in the list.
         */
        public Message getMessage( int index ) {
            return messageList.get( index );
        }

        /**
         * This method adds a new message to the chat.
         *
         * @param msg a unit of message
         * @return messageList.add( msg ) adds a new message to the end of the list.
         */
        public void addMessage( Message msg ) {
            messageList.add( msg );
        }

        /**
         * This method deletes a message from the arraylist
         *
         * @param index a specified index.
         * @return messageList.delete( index ) deletes from the specified index.
         */
        public void deleteMessage( int index ) {
            messageList.remove( index );
        }

        /**
         * This method sets the status of the chat.
         *
         * @param status the status.
         */
        public void setStatus( int status ) {
            this.status = status;
        }

        /**
         * This method gets the status as an integer.
         *
         * @return status the status of the chat.
         */
        public int getStatus() {
            return status;
        }

        /**
         * This method gets the time left to return the item.
         *
         * @return timeLeft the time left to return the item back.
         */
        public long getTimeLeft() {
            timeLeft = notice.computeTimeLeft();
            return timeLeft;
        }

        /**
         * This method gets the notice
         *
         * @return notice the notice.
         */
        public Notice getNotice() {
            return notice;
        }

        /**
         * This method gets the notice owner.
         *
         * @return noticeOwner the owner of the notice.
         */
        public User getNoticeOwner() {
            return noticeOwner;
        }

        /**
         * This method gets the customer.
         *
         * @return customer the customer of the notice.
         */
        public User getCustomer() {
            return customer;
        }

        /**
         *
         * @param chat
         * @return -1 if this chat should be listed higher on the list ( this chat recieved a message more recently )
         * @return 1 if this chat should be listed lower on the list ( this chat recieved a message more earlier )
         */
        public int compareTo( Chat chat ){
            if( this.messageList.get( messageList.size() - 1 ).compareTo( chat.messageList.get( messageList.size() - 1 ) ) > 0 ){
                return -1;
            }
            if( this.messageList.get( messageList.size() - 1 ).compareTo( chat.messageList.get( messageList.size() - 1 ) ) < 0 ){
                return 1;
            }
            else{
                return 0;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Chat chat = (Chat) o;
            return Objects.equals(notice, chat.notice) &&
                    Objects.equals(noticeOwner, chat.noticeOwner) &&
                    Objects.equals(customer, chat.customer);
        }
    }
