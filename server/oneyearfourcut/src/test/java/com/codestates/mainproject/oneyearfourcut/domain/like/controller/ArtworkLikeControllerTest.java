package com.codestates.mainproject.oneyearfourcut.domain.like.controller;

import com.codestates.mainproject.oneyearfourcut.domain.Like.controller.ArtworkLikeController;
import com.codestates.mainproject.oneyearfourcut.domain.Like.service.ArtworkLikeService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.codestates.mainproject.oneyearfourcut.global.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.codestates.mainproject.oneyearfourcut.global.util.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtworkLikeController.class)
@MockBean({JpaMetamodelMappingContext.class, ClientRegistrationRepository.class})
@AutoConfigureRestDocs
public class ArtworkLikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtworkLikeService artworkLikeService;

    @Autowired
    private Gson gson;

    @TestConfiguration
    static class DefaultConfigWithoutCsrf extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            super.configure(http);
            http.csrf().disable();
        }
    }

    @Test
    @WithMockUser(password = "0000")
    void putArtworkLikeTest() throws Exception {
        Long memberId = 1L;
        Long galleryId = 1L;
        Long artworkId = 1L;

        willDoNothing().given(artworkLikeService).updateArtworkLike(any(Long.class), any(Long.class), any(Long.class));

        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.put("/galleries/{gallery-id}/artworks/{artwork-id}/likes", galleryId, artworkId)
                        .header("Authorization", "Bearer (AccessToken)")
                        .accept(MediaType.APPLICATION_JSON)
        );

        result.andExpect(status().isOk())
                .andDo(document("put-artworkLike",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                headerWithName("Authorization").description("JWT - Access Token")
                        ),
                        pathParameters(
                                parameterWithName("gallery-id").description("전시관 식별자"),
                                parameterWithName("artwork-id").description("작품 식별자")
                        )
                ));
    }
}