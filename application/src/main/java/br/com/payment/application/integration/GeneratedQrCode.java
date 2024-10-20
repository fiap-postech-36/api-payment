package br.com.payment.application.integration;

import br.com.payment.application.exception.MercadoPagoIntegrationException;
import br.com.payment.application.gateway.IntegrationLinkPaymentGateway;
import br.com.payment.domain.core.domain.entities.external.QrCode;
import br.com.payment.infra.feign.client.MercadoPagoQrCodeClient;
import br.com.payment.infra.feign.presenter.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeneratedQrCode implements IntegrationLinkPaymentGateway {

    private final MercadoPagoQrCodeClient client;

    @Override
    public QrCode generatedQrCode(PaymentRequest request) {
        try {
            return client.generatedQrCode(request);
        } catch (Exception e) {
            throw new MercadoPagoIntegrationException("Integração com mercado pago falhou");
        }
    }
}
