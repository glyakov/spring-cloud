package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return ProductDto"
    request {
        method GET()
        url("/v1/product")
    }
    response {
        status 200
        body(file("./responses/product.json"))
        bodyMatchers {
            jsonPath('$.id', byType())
            jsonPath('$.name', byType())
            jsonPath('$.price', byType())
        }
        headers {
            contentType(applicationJson())
        }
    }
}