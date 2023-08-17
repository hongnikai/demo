package com.example.entity;

import org.apache.commons.lang3.StringUtils;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;
import java.util.Properties;

public class ADemo {

    public static LdapConfig getLdapContext() {
        Properties properties= new Properties();
//        String ldapUserName = "kteam\administrator";//AD管理员系统的账号
        String ldapPassword = "abc123==";//AD管理员系统的password
        String ldapIP = "192.168.21.116";//ad域的ip地址
        String ldapPost = "389";//ad域的port ,默认为389
        String ladpSecurityAuthentication = "simple";

        String ldapURL = "ldap://" + ldapIP + ":" + ldapPost;// ldap://ip:port

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");// LDAP工厂类
        properties.put(Context.SECURITY_AUTHENTICATION, ladpSecurityAuthentication);//LDAP访问安全级别："none","simple","strong"
//        properties.put(Context.SECURITY_PRINCIPAL, ldapUserName);
        properties.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        properties.put(Context.PROVIDER_URL, ldapURL);
//        try {
//            return new InitialLdapContext(properties, null);
//        } catch (Exception e) {
////            LOG.info("AD域服务连接认证失败");
//            System.out.println("AD域服务连接认证失败");
//            e.printStackTrace();
//        }
        LdapConfig ldapConfig = new LdapConfig();

        return ldapConfig;
    }
    static LdapContext ldapContext = null;
    static LdapConfig ldapConfig = null;

    private static LdapContext ldapConnect() {

        LdapConfig config = new LdapConfig();
        config.setUserDn("kteam\\administrator");
        config.setUserPwd("abc123==");
        String ldapURL = "ldap://" + "192.168.21.116" + ":" + "389";// ldap://ip:port
        config.setUrl(ldapURL);
        String username = config.getUserDn();;
        String encodePassWd = config.getUserPwd();
        String server = config.getUrl();
        try {
            Hashtable<String, String> env = new Hashtable<>();
            //用户名称，cn,ou,dc 分别：用户，组，域
            env.put(Context.SECURITY_PRINCIPAL, username);
            //用户密码 cn 的密码
            env.put(Context.SECURITY_CREDENTIALS, encodePassWd);
            //url 格式：协议://ip:端口/组,域   ,直接连接到域或者组上面
            env.put(Context.PROVIDER_URL, server);
            //LDAP 工厂
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            //验证的类型     "none", "simple", "strong"
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            LdapContext context = new InitialLdapContext(env, null);
            ldapContext = context; //在类中定义了常量来保存连接后的内容以便后续使用
            ldapConfig = config;
            ldapConfig.setBase("dc=kteam,dc=com");
            System.out.println("Login Ldap Server " + username + " Successful...");
            return ldapContext;
        } catch (NamingException e) {
            System.out.println("Login Ldap Server " + username + " fail...");
            System.out.println("ldap login error ：" + e.getExplanation());
            return null;
        }
    }

    public static void main(String[] args) {
        ldapConnect();

       getUserDN("zjiang");
    }

    /**
     * 断开连接
     */
    private static void ldapDisconnect() {
        if (null != ldapContext) {
            try {
                ldapContext.close();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getUserDN(String username) {
        String userDN = "dc=kteam,dc=com";
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration en = ldapContext.search(ldapConfig.getBase(),
                    "sAMAccountName=" + username, constraints);
            if (en == null) {
                System.out.println("Have NO such user!");
            }
            if (!en.hasMoreElements()) {
                System.out.println("Have NO such user!");
            }
            while (en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj instanceof SearchResult) {
                    SearchResult si = (SearchResult) obj;
                    System.out.println(si);
                    userDN += si.getName();
                    userDN += "," + ldapConfig.getBase();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in search user DN : " + e.toString());
        }
        return userDN;
    }

    private static Control[] connCtls = null;

    public static boolean authenticate(String userName, String password) {
        ldapConnect();
        boolean validate = false;
        String userDN = getUserDN(userName);
        if (StringUtils.isEmpty(userDN)) {
            return validate;
        }
        try {
            ldapContext.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
            ldapContext.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ldapContext.reconnect(connCtls);
            System.out.println(userDN + " is authenticated! ");
            validate = true;
        } catch (AuthenticationException e) {
            System.out.println(userDN + " is NOT authenticated! ");
            validate = false;
        } catch (NamingException e) {
            System.out.println(userDN + " is NOT authenticated! ");
            validate = false;
        }
        ldapDisconnect();
        return validate;
    }





}
