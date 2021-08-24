import gql from 'graphql-tag';

// //resolver
// const resolverMap = {
//   FieldValue: {
//     __resolveType(obj, context, info){
//       return obj.__typename
//     }
//   }
// }

export const ALL_SCHEMAS_QUERY = gql`
  query AllSchemasQuery  {
    cms {
      cmsContent {
        schemas {
        	key
        	name
        	description
        }
      }
    }
  }
`;

export const SCHEMA_DETAIL_QUERY = gql`
  query SchemaDetailQuery($schemaKey:String!) {
    cms {
      cmsContent {
        schema(key:$schemaKey) {
          key
          name
          description
          catalogs {
            key
            name
            catalogVersions {
              key
              name
              nrContentItems
            }
          }
        }
      }
    }
  }
`;

export const CONTENT_TYPE_DETAIL_QUERY = gql`
query ContentTypeDetailQuery($schemaKey:String!, $contentTypeKey:String!) {
  cms {
    cmsContent {
      schema(key:$schemaKey) {
        key
        name
        contentType(key: $contentTypeKey) {
          key
          name
          description
          fieldDefinitions {
            key
            name
            description
            fieldType
          }
        }
      }
    }
  }
}`;

export const CATALOG_LIST_QUERY = gql`
query CatalogListQuery($schemaKey:String!) {
  cms {
    cmsContent {
      schema(key:$schemaKey) {
        key
        name
        catalogs {
          key
          name
          catalogVersions {
            key
            name            
          }
        }
      }
    }
  }
}`;

export const CATALOG_DETAIL_QUERY = gql`
  query SchemaDetailQuery($schemaKey:String!, $catalogKey: String!) {
    cms {
      cmsContent {
        schema(key:$schemaKey) {
          id
          key
          name
          description
          catalog(key:$catalogKey) {
            id
            key
            name
            description
            catalogVersions {
              id
              key
              name
              nrContentItems
            }
          }
        }
      }
    }
  }
`;

export const CATALOG_DETAIL_MUTATION = gql`
mutation UpdateCatalog($schemaKey:String!, $catalogKey:String!, $name:String, $description:String) {
  updateCatalog(input: {
    key: $catalogKey
    schemaKey:$schemaKey
    name: $name
    description: $description
  }) {
    catalog {
      key
      name
    }
  }
}
`;

export const CONTENT_ITEM_LIST_QUERY = gql`query ContentItemListQuery($schemaKey:String!, $catalogKey: String!, $catalogVersionKey:String!) {
  cms {
    cmsContent {
      schema(key:$schemaKey) {
        key
        name
        contentType(key:"article") {
          key
        }
        catalog(key:$catalogKey) {
          key
          name
          catalogVersion(key:$catalogVersionKey) {
            key
            contentItems {
              key
              name
              contentType {
                key
                name
              }
            }
          }
        }
      }
    }
  }
}`;

export const CONTENT_ITEM_DETAIL_QUERY = gql`
query ContentItemDetailQuery($schemaKey:String!, $catalogKey: String!, $catalogVersionKey:String!, $contentItemKey:String!) {
  cms {
    cmsContent {
      schema(key:$schemaKey) {
        key
        name
        contentType(key:"article") {
          key
        }
        catalog(key:$catalogKey) {
          key
          name
          catalogVersion(key:$catalogVersionKey) {
            key
            contentItem(key:$contentItemKey) {
              key
              contentType {
                key
              }
              fields {
                key
                fieldDefinition {
                  fieldType
                }
                value {
                  ... on StringFieldValue {
                    content
                  }
        					... on LocalizedStringFieldValue {
                    content
                  }
                  ... on TextFieldValue {
                    mimeType
                    content
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
`;

export const ALL_CONTENT_ITEMS_QUERY = gql`
query AllContentItemsQuery {
  cms {
    cmsContent {
      schemas {
        catalogs {
          catalogVersions {
            contentItems {
              key              
              contentType {
                key
              }
            }
          }
        }
      }
    }
  }
}`

export const CONTENT_ITEM_UPDATE_MUTATION = gql`
mutation UpdateContentItem($schemaKey:String!, $catalogKey:String!, $catalogVersionKey:String!, $contentItemKey: String!, $fields: [UpdateFieldValueInput]!) {
  updateContentItem(input: {
    contentItemKey: $contentItemKey,
    catalogVersionKey: $catalogVersionKey,
    catalogKey: $catalogKey,
    schemaKey: $schemaKey,
    fields: $fields}) {
    contentItem {
      key
    }
  }
}
`;

export const SCHEMA_CREATE_MUTATION = gql`
mutation SchemaCreateMutation($key: String!, $name: String, $description: String) {
  createSchema(input: {
      key: $key,
      name: $name,
      description: $description
    }) {
      schema {
        key
        name
        description
      }
  }
}
`;
