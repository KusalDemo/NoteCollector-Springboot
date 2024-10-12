package lk.ijse.NoteCollector_Springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// Security Related implementations for web application .web 👇
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Value("${secure.username}")
    private String username;

    @Value("${secure.password}")
    private String password;

    @Value("${secure.role}")
    private String role;

    @Bean
    SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Deprecated
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        //Principal User
        UserDetails inMemoryUser = User.withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles(role)
                .build();
        return new InMemoryUserDetailsManager(inMemoryUser);
    }

    /*
        httpSecurity.csrf(AbstractHttpConfigurer::disable):
            මේක CSRF (Cross-Site Request Forgery) ආරක්ෂාව අක්‍රීය කරනවා.
            CSRF කියන්නේ මොනවාද කියනවානම්, malicious වෙබ් අඩවියකින් තවත් අය behalf එකෙන් unauthorized request එවීම වැළැක්වීමක්. මේක APIs වගේ use cases වල disable කරන්න පුළුවන්, හැබැයි සෑහෙන්න සැලකිලිමත් වෙන්න ඕනි.
        .authorizeHttpRequests():
            මේක HTTP request වලට authorization rules set කරන එකක්.
            ආරක්ෂාව හදා ගන්න පුළුවන්, මේකට request process කරන්න විදිහ setup කරනවා.
        .anyRequest().authenticated():
            ඕනෑම incoming request එකක් authenticated වෙන්න ඕනි කියලා කියනවා.
            උදාහරණයක් විදියට, user එකෙක් login වෙලා ඉන්න එක හෝ valid credentials (session එකක්, token එකක්) provide කරන එක ඕනේ.
        .and():
            මේක chaining කියන concept එකක්. දැනට තිබෙන configuration එක close කරලා, තවත් එකක් add කරන්න පුළුවන් කියන එක.
        .httpBasic():
            මේක Basic HTTP Authentication enable කරන එක. මෙහිදි user එකගේ username-password HTTP headers වලින් pass කරන්නේ (Base64 encoding එකෙන්).
            මේක simple authentication එකක්, හැබැයි HTTPS අරන් යන්න පුරුදු වෙන එක හොඳයි, නැතිනම් insecure.
        return httpSecurity.build();:
            මේක දීලා තියෙන security settings වලින් HttpSecurity object එකක් build කරන එක.
            මේක අවසාන අසුරක්, configuration එක apply කරන්න.
        සාමාන්‍යයෙන්, මේක CSRF අක්‍රීය කරලා, සෑම request එකක්ම authenticated වෙන්න ඕනි කියලා, basic HTTP authentication enable කරලා, security configuration එක build කරනවා.
    */
}
