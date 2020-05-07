package com.example.demo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ShortcutsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnEmptyJsonForNotLoggedIn() throws Exception {
        this.mockMvc.perform(get("/shortcutStatus")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{}")));
    }

    @Test
    public void shouldReturnNotesShortcut() throws Exception {
        this.mockMvc.perform(get("/shortcutStatus").sessionAttr("user", "bdangabriel@gmail.com")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("notesShortcut")));
    }

    @Test
    public void shouldReturnProfileShortcut() throws Exception {
        this.mockMvc.perform(get("/shortcutStatus").sessionAttr("user", "bdangabriel@gmail.com")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("profileShortcut")));
    }
}