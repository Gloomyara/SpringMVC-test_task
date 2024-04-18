package ru.antonovmikhail.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import ru.antonovmikhail.order.model.Order;
import ru.antonovmikhail.user.model.User;
import ru.antonovmikhail.util.Views;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class JsonViewTests {
    @Test
    public void whenUsePublicView_thenOnlyPublicSerialized()
            throws JsonProcessingException {
        User user = new User(UUID.randomUUID(), "Buba", "whtvr@mail.org", null);
        List<Order> orderList = List.of(new Order(UUID.randomUUID(), BigDecimal.valueOf(100), user, "wtvr", "wtvr", false ));
        user.setOrderList(orderList);

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);;
        String result1 = mapper
                .writerWithView(Views.UserSummary.class)
                .writeValueAsString(user);

        assertThat(result1, containsString("Buba"));
        assertThat(result1, not(containsString("whtvr@mail.org")));
        String result2 = mapper
                .writerWithView(Views.UserDetails.class)
                .writeValueAsString(user);
        assertThat(result2, containsString("Buba"));
        assertThat(result2, containsString("whtvr@mail.org"));
        System.out.println(result2);
    }
}
