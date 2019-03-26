package org.github.flytreeleft.nexus3.keycloak.plugin.internal;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.github.flytreeleft.nexus3.keycloak.plugin.internal.token.UsernamePasswordOnetimePasswordToken;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class InternalOneTimePasswordHandlingTest {

    @Test
    public void test_OnetimePasswordRegex_PasswordWithOtpIsPassed_PasswordFieldWithoutOtpAndOtpFieldShouldBeSet() {
        // Arrange
        UsernamePasswordToken passwordToken = new UsernamePasswordToken("user", "mypassword{213546}");
        // Act
        UsernamePasswordOnetimePasswordToken onetimePasswordToken = new UsernamePasswordOnetimePasswordToken(passwordToken);
        // Assert
        assertThat(onetimePasswordToken.getOneTimePassword()).isEqualToIgnoringCase("213546");
        assertThat(new String(onetimePasswordToken.getPassword())).isEqualTo("mypassword");
    }

    @Test
    public void test_OnetimePasswordRegex_PasswordWithoutOtpIsPassed_NothingShouldBeSet() {
        // Arrange
        UsernamePasswordToken passwordToken = new UsernamePasswordToken("user", "mypassword");
        // Act
        UsernamePasswordOnetimePasswordToken onetimePasswordToken = new UsernamePasswordOnetimePasswordToken(passwordToken);
        // Assert
        assertThat(onetimePasswordToken.getOneTimePassword()).isEqualToIgnoringCase(null);
        assertThat(new String(onetimePasswordToken.getPassword())).isEqualTo("mypassword");
    }
}
