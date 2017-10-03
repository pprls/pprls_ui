package org.pprls.ui.event;

import org.pprls.ui.domain.Item;
import org.pprls.ui.view.DashboardViewType;

import java.util.Collection;
import java.util.Set;

//import com.vaadin.demo.dashboard.domain.Transaction;
//import com.vaadin.demo.dashboard.view.DashboardViewType;

/*
 * Event bus events used in Dashboard are listed here as inner classes.
 */
public abstract class DashboardEvent {

    public static final class UserLoginRequestedEvent {
        private final String userName, password;

        public UserLoginRequestedEvent(final String userName,
                final String password) {
            this.userName = userName;
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class BrowserResizeEvent {

    }

    public static class UserLoggedOutEvent {

    }

    public static class NotificationsCountUpdatedEvent {
    }

    public static final class ReportsCountUpdatedEvent {
        private final int count;

        public ReportsCountUpdatedEvent(final int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

    }

//    public static final class TransactionReportEvent {
//        private final Collection<Transaction> transactions;
//
//        public TransactionReportEvent(final Collection<Transaction> transactions) {
//            this.transactions = transactions;
//        }
//
//        public Collection<Transaction> getTransactions() {
//            return transactions;
//        }
//    }
//
    public static final class PostViewChangeEvent {
        private final DashboardViewType view;

        public PostViewChangeEvent(final DashboardViewType view) {
            this.view = view;
        }

        public DashboardViewType getView() {
            return view;
        }
    }

    public static class CloseOpenWindowsEvent {
    }

    public static class ProfileUpdatedEvent {
    }

    // added by K. Apostolou

    public static class SelectItemEvent {
        private final Set<Item> items;

        public SelectItemEvent(final Set<Item> items) {
            this.items = items;
        }

        public boolean isItemSelected(){
            if(items==null ||items.isEmpty()){
                return false;
            }else {
                return true;
            }
        }

        public Item getFirstItem() {
            if (!items.isEmpty()) {
                return (Item) items.toArray()[0];
            }else{
                throw new RuntimeException("No item selected");
            }

        }
    }

}
