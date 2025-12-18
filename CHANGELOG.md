# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- 새로운 기능 추가 예정

### Changed
- 기존 기능 개선 및 변경 예정

### Fixed
- 알려진 버그 수정 예정

### Removed
- 더 이상 사용되지 않는 기능 제거 예정

---

## [2.0.0-dev] - 2025-12-18

### Added
- **Nexo** 연동 지원
    - Item
    - Event
    - Objective
    - Condition
- **CraftEngine** 연동 지원
    - Item
    - Event
    - Objective
    - Condition
- **ItemsAdder** 연동 지원
    - Item
    - Event
    - Objective
    - Condition

### Changed
- 내부 구조 리팩토링
- API 일관성 개선

### Deprecated
- 기존 레거시 Objective API (차기 버전에서 제거 예정)

### Removed
- 더 이상 유지보수되지 않는 레거시 코드 제거

### Fixed
- 일부 아이템 이벤트가 정상적으로 트리거되지 않던 문제 수정
- Objective 카운트 처리 오류 수정

### Security
- 외부 플러그인 연동 시 권한 검증 강화
