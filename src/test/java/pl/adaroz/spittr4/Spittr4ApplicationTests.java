package pl.adaroz.spittr4;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.adaroz.spittr4.data.SpitterRepository;
import pl.adaroz.spittr4.data.SpittleRepository;
import pl.adaroz.spittr4.web.HomeController;
import pl.adaroz.spittr4.web.SpitterController;
import pl.adaroz.spittr4.web.SpittleController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class Spittr4ApplicationTests {

    @Test
    public void testHomePage() throws Exception {
        HomeController controller = new HomeController();
        MockMvc mockMvc = standaloneSetup(controller)
                .build();

        mockMvc.perform(get("/homepage"))
                .andExpect(view().name("/home"));
    }

    @Test
    public void shouldShowPagedSpittles() throws Exception {
        List<Spittle> expectedSpittles = createSpittleList(50);
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findSpittles(238900, 50))
                .thenReturn(expectedSpittles);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .build();

        mockMvc.perform(get("/spittles?max=238900&count=50"))
                .andExpect(view().name("/spittleList"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
    }

    @Test
    public void testSpittle() throws Exception {
        Spittle expectedSpittle = new Spittle("Hello", new Date());
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/spittles/12345"))
                .andExpect(view().name("/spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle", expectedSpittle));
    }

    @Test
    public void shouldShowRegistration() throws Exception {
        SpitterRepository mockRepository = mock(SpitterRepository.class);
        SpitterController controller = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/spitter/register"))
                .andExpect(view().name("/registerForm"));
    }

    private List<Spittle> createSpittleList(int count) {
        ArrayList<Spittle> spittles = new ArrayList<>();
        for (int i = 0; i < count; i++)
            spittles.add(new Spittle("Spittle " + i, new Date()));
        return spittles;
    }

}
