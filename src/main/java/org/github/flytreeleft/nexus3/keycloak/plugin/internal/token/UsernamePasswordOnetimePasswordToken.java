package org.github.flytreeleft.nexus3.keycloak.plugin.internal.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernamePasswordOnetimePasswordToken extends UsernamePasswordToken {

    private static final String OTP_REGEX = "(.*)\\{(\\d{6})\\}$";
    private static final Pattern P_OTP_REGEX = Pattern.compile(OTP_REGEX);

    private String oneTimePassword;

    public UsernamePasswordOnetimePasswordToken(UsernamePasswordToken t) {
        super(t.getUsername(), t.getPassword());
        this.oneTimePassword = extractOneTimePasswordFromPassword(t.getPassword());
    }


    private String extractOneTimePasswordFromPassword(char[] password) {
        Matcher regexMatcher = P_OTP_REGEX.matcher(new String(password));
        if (regexMatcher.matches()) {
            super.setPassword(regexMatcher.group(1).toCharArray());
            return regexMatcher.group(2);
        }
        return null;
    }

    public String getOneTimePassword() {
        return this.oneTimePassword;
    }

}
