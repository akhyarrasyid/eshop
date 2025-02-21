package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verifyNoInteractions;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class HomePageControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private HomePageController homePageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHomePage() {
        String viewName = homePageController.homePage(model);
        assertEquals("homePage", viewName);
        assertNotNull(viewName);
        verifyNoInteractions(model);
    }

    @Test
    void testHomePageWithNullModel() {
        String viewName = homePageController.homePage(null);
        assertEquals("homePage", viewName);
        assertNotNull(viewName);
    }
}
