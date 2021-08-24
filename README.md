# kingfisher
A graphql headless CMS

## Status
A fun hobby project.


## Sample graphql queries

### Query single content item article.about

```json
        {
          cms {
            cmsContent {
              schemas {
                key
                name
                description
                catalog(key:"admin") {
                  catalogVersion(key:"draft") {
                    key
                    contentItem(key:"article.about") {
                      key
                      fields {
                        key
                        value {
                          ... on StringFieldValue {
                            content
                          }
                          ... on TextFieldValue {
                            content
                          }
                          ... on LocalizedStringFieldValue {
                            contents {
                              locale
                              value
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
```

