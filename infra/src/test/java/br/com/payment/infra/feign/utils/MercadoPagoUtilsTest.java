package br.com.payment.infra.feign.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

public class MercadoPagoUtilsTest {

    @Test
    public void testExpirationQrCode() {
        LocalDateTime expirationTime = MercadoPagoUtils.expirationQrCode();

        LocalDateTime expectedMinTime = LocalDateTime.now().plusHours(2).minusMinutes(1);
        LocalDateTime expectedMaxTime = LocalDateTime.now().plusHours(2).plusMinutes(1);

        Assertions.assertTrue(
                expirationTime.isAfter(expectedMinTime) && expirationTime.isBefore(expectedMaxTime),
                "A data de expiração está fora do intervalo esperado de 2 horas."
        );
    }

    @Test
    public void testValidateQrCodeExpirationWithinValidTime() {
        LocalDateTime validDateTime = LocalDateTime.now().plusHours(1);

        Assertions.assertDoesNotThrow(() ->
                        MercadoPagoUtils.validateQrCodeExpiration(validDateTime),
                "O método lançou uma exceção, mas o QR Code ainda deveria estar válido."
        );
    }

    @Test
    public void testValidateQrCodeExpirationAfterExpirationTime() {

        LocalDateTime expiredDateTime = LocalDateTime.now().plusHours(3);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> MercadoPagoUtils.validateQrCodeExpiration(expiredDateTime),
                "Esperava-se uma exceção de expiração para um QR Code expirado."
        );

        Assertions.assertEquals("Pagamento expirado.", exception.getMessage());
    }

}

