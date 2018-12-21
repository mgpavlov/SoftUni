package cars.controllers;

import cars.dto.views.sale.SaleInfoViewDto;
import cars.dto.views.sale.SalesInfoXmlViewDto;
import cars.serializers.Serializer;
import cars.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SaleController {
    private static final String SALES_DISCOUNTS_CARS_JSON_INPUT_PATH = "/src/main/resources/json/output/sales-discounts.json";
    private static final String SALES_DISCOUNTS_CARS_XML_INPUT_PATH = "src/main/resources/xml/output/sales-discounts.xml";

    private final SaleService saleService;
    private final Serializer jsonSerializer;
    private final Serializer xmlSerializer;

    @Autowired
    public SaleController(SaleService saleService, @Qualifier("json") Serializer jsonSerializer, @Qualifier("xml") Serializer xmlSerializer) {
        this.saleService = saleService;
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
    }

    public void importSales() {
        this.saleService.createMany();
    }

    public void exportSalesWithAppliedDiscountsToJson() {
        List<SaleInfoViewDto> salesWithAppliedDiscount = this.saleService.getSalesWithAppliedDiscount();
        this.jsonSerializer.serialize(salesWithAppliedDiscount, SALES_DISCOUNTS_CARS_JSON_INPUT_PATH);
    }

    public void exportSalesWithAppliedDiscountsToXml() {
        List<SaleInfoViewDto> salesWithAppliedDiscount = this.saleService.getSalesWithAppliedDiscount();
        SalesInfoXmlViewDto salesInfoXmlViewDto = new SalesInfoXmlViewDto();
        salesInfoXmlViewDto.setSaleInfoViewDtos(salesWithAppliedDiscount);
        xmlSerializer.serialize(salesInfoXmlViewDto, SALES_DISCOUNTS_CARS_XML_INPUT_PATH);
    }
}
