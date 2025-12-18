# Changelog
All notable changes to this project will be documented in this file.

The format follows [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- Planned addition of new features

### Changed
- Planned improvements and changes to existing features

### Fixed
- Planned fixes for known issues

### Removed
- Planned removal of deprecated features

---

## [2.0.0-dev] - 2025-12-18

### Added
- Support for `Nexo` integration
  - Item
  - Event
  - Objective
  - Condition
- Support for `CraftEngine` integration
  - Item
  - Event
  - Objective
  - Condition
- Support for `ItemsAdder` integration
  - Item
  - Event
  - Objective
  - Condition

### Changed
- Internal architecture refactored
- Improved API consistency

### Deprecated
- Legacy Objective API (scheduled for removal in a future release)

### Removed
- Removed legacy code that is no longer maintained

### Fixed
- Fixed an issue where some item events were not triggered correctly
- Fixed errors in Objective count handling
- Fixed an issue where an error was displayed in the BetonQuestAddon when disabled

### Security
- Strengthened permission validation for external plugin integrations
