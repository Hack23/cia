workflow "Find Secrets" {
  on = "push"
  resolves = ["max/secret-scan"]
}

action "max/secret-scan" {
  uses = "max/secret-scan@master"
}