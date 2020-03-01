package com.xp.glasses;

import javax.servlet.http.HttpSession;
import java.util.HashMap;


/**
 * session 上下文对象
 * @author Mrxiong
 */
public class SessionContext {

    private static SessionContext instance;

    private HashMap<String,HttpSession> sessionMap;

    private SessionContext() {
        sessionMap = new HashMap();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }

}
