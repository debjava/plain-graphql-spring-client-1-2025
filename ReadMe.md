Global GraphQL site for testing

https://countries.trevorblades.com/graphql

query Country {
    country(code: "BR") {
        code
        capital
    }
}


query Countries {
    countries(filter: { currency: { eq: "USD" } }) {
        currencies
		name
    }
}



query Continent {
    continent(code: "EU") {
        countries {
            capital
            code
            currency
            languages {
                name
                code
            }
        }
    }
}




query Countries {
    countries(filter: { code: { eq: "VA" }, continent: { eq: "EU" } }) {
        capital
        code
        awsRegion
        languages {
            name
            native
        }
    }
}


https://medium.com/@praveenkumarsingh/different-ways-to-call-graphql-api-endpoints-in-spring-boot-4f409aaa101e#:~:text=In%20GraphQL%2C%20query%20variables%20are,an%20input%20to%20the%20query.


