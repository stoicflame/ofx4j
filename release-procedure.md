1. Replace all instances of old_version with new_version (do a replaceAll operation for now, until you want to figure out how to apply the maven-release plugin)
2. `mvn clean install` (to run all the tests)
3. Commit and tag (e.g. `git tag -a v1.7 -m "Version 1.7"`).
4. `mvn clean deploy -P release` (your new version should now be deployed)
5. Push.
6. Reset HEAD to a snapshot version.
7. [Create the release](https://github.com/stoicflame/ofx4j/releases).
8. Update the README with the new version.