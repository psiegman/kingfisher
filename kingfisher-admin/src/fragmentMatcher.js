import { IntrospectionFragmentMatcher } from 'apollo-cache-inmemory';

// https://github.com/apollographql/apollo-client/issues/2303
const apolloFragmentMatcher = new IntrospectionFragmentMatcher({
  introspectionQueryResultData: {
    __schema: {
      types: [
        {
          kind: "UNION",
          name: "FieldValue",
          possibleTypes: [
            { name: "StringFieldValue" },
            { name: "LocalizedStringFieldValue" },
            { name: "TextFieldValue" },
          ],
        },
      ],
    },
  }
});

export default apolloFragmentMatcher;