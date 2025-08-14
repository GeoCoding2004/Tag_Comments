// @ts-check
module.exports = [
  {
    files: ["**/*.ts"],
    parser: "@typescript-eslint/parser",
    parserOptions: {
      project: ["tsconfig.json"],
      sourceType: "module",
    },
    extends: [
      "eslint:recommended",
      "plugin:@typescript-eslint/recommended",
      "plugin:@typescript-eslint/recommended-requiring-type-checking",
      "plugin:@angular-eslint/recommended",
    ],
    rules: {
      "@angular-eslint/directive-selector": [
        "error",
        { type: "attribute", prefix: "app", style: "camelCase" },
      ],
      "@angular-eslint/component-selector": [
        "error",
        { type: "element", prefix: "app", style: "kebab-case" },
      ],
    },
  },
  {
    files: ["**/*.html"],
    extends: ["plugin:@angular-eslint/template/recommended", "plugin:@angular-eslint/template/accessibility"],
    rules: {},
  },
];
