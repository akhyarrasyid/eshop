package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verifyNoInteractions;

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
        // Act
        String viewName = homePageController.homePage(model);

        // Assert
        assertEquals("homePage", viewName);
        assertNotNull(viewName);
        verifyNoInteractions(model);
    }

    @Test
    void testHomePageWithNullModel() {
        // Act
        String viewName = homePageController.homePage(null);

        // Assert
        assertEquals("homePage", viewName);
        assertNotNull(viewName);
    }
}
