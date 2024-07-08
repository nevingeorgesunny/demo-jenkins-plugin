# Cloudbees Onboarding :  Jenkins Plugin Learning

This repository tracks my progress in developing the `Practical Work: Developing a Jenkins plugin yourself` mentioned here  [Here](https://engineering.beescloud.com/docs/engineering-cbci/latest/onboarding/practical-work-plugin)

## Progress Overview

| Level   | Description                                                                 | Status |
|---------|-----------------------------------------------------------------------------|--------|
| Level 0 | Bootstrap a new project for your plugin                                     | ✅      |
| Level 1 | Configure the Global Configuration page                                     | ✅  |
| Level 2 | Implement name validation                                                   | ✅      |
| Level 3 | Set up connection configuration                                             | ❌      |
| Level 4 | Validate configuration with a "Test Connection" button                      | ❌      |
| Level 5 | Add a payload for the connection                                            | ❌      |
| Level 6 | Set up a list of categories                                                 | ❌      |
| Level 7 | Add a "build step" for Freestyle projects                                   | ❌      |
| Level 7b| Make the "build step" compatible with Pipeline projects                     | ❌      |
| Level 8 | Store references to the latest 5 builds                                     | ❌      |
| Level 9 | Display references of the latest 5 builds                                   | ❌      |
| Level 10| Store latest job names per category                                         | ❌      |
| Level 11| Display last job DisplayName                                                | ❌      |
| Level 12| Support job renaming                                                       | ❌      |


## How to Run the Plugin Locally

1. Clone the repository:

2. Run the plugin using Maven:
   ```sh
   mvn hpi:run
   ```

This command will start Jenkins with the plugin running locally.

