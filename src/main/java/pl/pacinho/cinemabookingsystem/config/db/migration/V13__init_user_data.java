package pl.pacinho.cinemabookingsystem.config.db.migration;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;
import pl.pacinho.cinemabookingsystem.config.security.CryptoConfig;


@RequiredArgsConstructor
@Component
public class V13__init_user_data extends BaseJavaMigration {

    private final CryptoConfig cryptoConfig;

    @Override
    public void migrate(Context context) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
        jdbcTemplate.execute("insert into cinema_user(name, login, role, password) values ('user1', 'user1', 'ROLE_USER', '" + cryptoConfig.passwordEncoder().encode("user1") + "')");
        jdbcTemplate.execute("insert into cinema_user(name, login, role, password) values ('user2', 'user2', 'ROLE_USER', '" + cryptoConfig.passwordEncoder().encode("user2") + "')");
    }
}
