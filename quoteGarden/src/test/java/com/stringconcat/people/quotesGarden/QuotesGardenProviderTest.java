package com.stringconcat.people.quotesGarden;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.serverError;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.stringconcat.people.quotesGarden.QuoteGardenProviderKt.DEFAULT_QUOTE;
import static com.stringconcat.people.quotesGarden.QuoteGardenProviderKt.PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 80)
public class QuotesGardenProviderTest {

    @Test
    public void requestQuoteSuccess() {
        stubFor(get(PATH)
                .willReturn(ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody(RESPONSE_PAYLOAD)));
        var quoteProvider = new QuoteGardenProvider();
        quoteProvider.changeUrl("http://localhost");
        var quote = quoteProvider.randomQuote();
        assertEquals(quote, QUOTE);
    }

    @Test
    public void requestQuoteError() {
        stubFor(get(PATH)
                .willReturn(serverError()));
        var quoteProvider = new QuoteGardenProvider();
        quoteProvider.changeUrl("http://localhost");
        var quote = quoteProvider.randomQuote();
        assertEquals(quote, DEFAULT_QUOTE);
    }

    private static final String QUOTE = "Обычный человек ищет признания в глазах окружающих, называя это уверенностью в себе. Воин ищет безупречности в собственных глазах и называет это смирением. ";
    private static final String RESPONSE_PAYLOAD = "{\"quoteText\":\"" + QUOTE + "\", \"quoteAuthor\":\"Карлос Кастанеда\", \"senderName\":\"\", \"senderLink\":\"\", \"quoteLink\":\"http://forismatic.com/ru/38c4359a4a/\"}";
}
